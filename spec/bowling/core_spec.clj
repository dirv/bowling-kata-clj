(ns bowling.core-spec
  (:require [speclj.core :refer :all]
            [bowling.core :refer :all]))

(defn new-score [rolls]
  (apply + (map-indexed (fn [i v]
                          (cond
                            (and (< i (- (count rolls) 2))
                                 (= 10 v))
                            (+ v (nth rolls (+ i 2)) (nth rolls (+ i 1)))
                            (and (< i (- (count rolls) 2))
                                 (= 10 (+ (nth rolls (inc i)) v)))
                            (+ v (nth rolls (+ i 2)))
                            :else v)) rolls)))

(defn- score [rolls]
  (new-score (vec rolls)))

(describe "new-score"
  (it "scores a zero game"
    (should= 0 (new-score (repeat 20 0))))
  (it "scores a game with all ones"
    (should= 20 (new-score (repeat 20 1))))
  (it "scores a game with a spare"
    (should= 16 (new-score (concat '(5 5 3) (repeat 17 0)))))
  (it "scores a game with a strike"
    (should= 20 (new-score (concat '(10 5) (repeat 18 0)))))
  (it "scores a perfect game"
    (should= 300 (new-score (repeat 12 10)))))

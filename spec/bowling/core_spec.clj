(ns bowling.core-spec
  (:require [speclj.core :refer :all]
            [bowling.core :refer :all]))

(defn new-score [rolls]
  (apply + (map-indexed #(if (= %2 '(5 5))
                  (+ 10 (first (get (vec rolls) (inc %1))))
                  (apply + %2))
               rolls)))

(defn score [rolls]
  (new-score (partition-all 2 rolls)))

(describe "score"
  (it "scores a zero game"
    (should= 0 (score (repeat 20 0))))
  (it "scores a game with all ones"
    (should= 20 (score (repeat 20 1))))
  (it "scores a game with a spare"
    (should= 16 (score (concat '(5 5 3) (repeat 17 0))))))

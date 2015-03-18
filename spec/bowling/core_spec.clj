(ns bowling.core-spec
  (:require [speclj.core :refer :all]
            [bowling.core :refer :all]))

(defn- full-scores [rolls]
  (take 21 (concat rolls (repeat 0))))

; potential variations to show--total
; using take a loop/recur
(defn to-frames [rolls]
  (loop [frame '()
         all-frames '()
         remaining rolls]
    (cond (= 10 (count all-frames))
          all-frames
          (= '(10) frame)
          (recur '() (conj all-frames (conj frame (first remaining) (second remaining))) remaining)
          (= 10 (apply + frame))
          (recur '() (conj all-frames (conj frame (first remaining))) remaining)
          (= 2 (count frame))
          (recur '() (conj all-frames frame) remaining)
          :else
          (recur (conj frame (first remaining)) all-frames (rest remaining)))))

(defn- score [rolls]
  (apply + (flatten (to-frames (full-scores rolls)))))

(describe "new-score"
  (it "scores a zero game"
    (should= 0 (score (repeat 20 0))))
  (it "scores a game with all ones"
    (should= 20 (score (repeat 20 1))))
  (it "scores a game with a spare"
    (should= 16 (score (concat '(5 5 3) (repeat 17 0)))))
  (it "scores a game with a strike"
    (should= 30 (score (concat '(10 5 5) (repeat 18 0)))))
  (it "scores a perfect game"
    (should= 300 (score (repeat 12 10)))))

(ns bowling.core-spec
  (:require [speclj.core :refer :all]
            [bowling.core :refer :all]))

(describe "score"
  (it "scores a zero game"
    (should= 0 (score (repeat 20 0))))
  (it "scores a game with all ones"
    (should= 20 (score (repeat 20 1))))
  (it "scores a game with a spare"
    (should= 16 (score (concat '(5 5 3) (repeat 17 0)))))
  (it "scores a game with a strike"
    (should= 30 (score (concat '(10 5 5) (repeat 17 0)))))
  (it "scores a perfect game"
    (should= 300 (score (repeat 12 10)))))

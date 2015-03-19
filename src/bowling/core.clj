(ns bowling.core)

(defn- strike? [remaining]
  (= 10 (first remaining)))

(defn- spare? [remaining]
  (= 10 (+ (first remaining) (second remaining))))

(defn- strike-bonus [remaining]
  (apply + (take 2 (rest remaining))))

(defn- spare-bonus [remaining]
  (nth remaining 2))

(defn- roll-score [remaining]
  (apply + (take 2 remaining)))

(defn- score-frame [[remaining score]]
  (cond
    (strike? remaining) [(drop 1 remaining) (+ score 10 (strike-bonus remaining))]
    (spare? remaining) [(drop 2 remaining) (+ score 10 (spare-bonus remaining))]
    :else [(drop 2 remaining) (+ score (roll-score remaining))]))

(defn score [rolls]
  (second (nth (iterate score-frame [rolls 0]) 10)))


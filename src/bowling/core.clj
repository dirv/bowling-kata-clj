(ns bowling.core)

(defn- strike? [remaining]
  (= 10 (first remaining)))

(defn- spare? [remaining]
  (= 10 (+ (first remaining) (second remaining))))

(defn- to-frame [[remaining frames]]
  (cond 
    (strike? remaining) 
    [(drop 1 remaining) (conj frames (take 3 remaining))]
    (spare? remaining) 
    [(drop 2 remaining) (conj frames (take 3 remaining))]
    :else
    [(drop 2 remaining) (conj frames (take 2 remaining))])
    )

(defn score [rolls]
  (apply + (flatten (second (nth (iterate to-frame [rolls '()]) 10)))))

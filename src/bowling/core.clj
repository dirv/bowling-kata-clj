(ns bowling.core)

(defn- strike? [remaining]
  (= 10 (first remaining)))

(defn- spare? [remaining]
  (= 10 (+ (first remaining) (second remaining))))

(defn- to-frames [remaining frames]
  (cond 
    (empty? remaining)
    frames
    (strike? remaining) 
    (to-frames (drop 1 remaining) (concat frames (list (take 3 remaining))))
    (spare? remaining) 
    (to-frames (drop 2 remaining) (concat frames (list (take 3 remaining))))
    :else
    (to-frames (drop 2 remaining) (concat frames (list (take 2 remaining))))))

(defn score [rolls]
  (apply + (flatten (take 10 (to-frames rolls '())))))

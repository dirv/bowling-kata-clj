(ns bowling.core)

(defn- strike? [remaining]
  (= 10 (first remaining)))

(defn- spare?  [remaining]
  (= 10 (+ (first remaining) (second remaining))))

(defn- to-frames [remaining frames]
  (if (empty? remaining)
    frames  
    (cond (strike? remaining) 
        (to-frames (drop 1 remaining) (conj frames (take 3 remaining)))
        (spare? remaining) 
        (to-frames (drop 2 remaining) (conj frames (take 3 remaining)))
        :else
        (to-frames (drop 2 remaining) (conj frames (take 2 remaining))))))

(defn score [rolls]
  (apply + (flatten (take-last 10 (to-frames rolls '())))))

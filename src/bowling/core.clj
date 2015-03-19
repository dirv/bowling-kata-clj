(ns bowling.core)

(defn- to-frames [remaining [frame & frames]]
  (if (empty? remaining)
    (conj frames frame)
  (cond (= '(10) frame)
        (to-frames remaining (conj frames (conj frame (first remaining) (second remaining)) '()))
        (= 10 (apply + frame))
        (to-frames remaining (conj frames (conj frame (first remaining)) '()))
        (= 2 (count frame))
        (to-frames remaining (conj frames frame '()))
        :else
        (to-frames (rest remaining) (conj frames (conj frame (first remaining)))))))

(defn score [rolls]
  (apply + (flatten (take-last 10 (to-frames rolls '())))))

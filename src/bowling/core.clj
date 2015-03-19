(ns bowling.core)

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

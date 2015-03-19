(ns bowling.core)

(defn- full-scores [rolls]
  (take 21 (concat rolls (repeat 0))))

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

(defn score [rolls]
  (apply + (flatten (to-frames (full-scores rolls))))) 

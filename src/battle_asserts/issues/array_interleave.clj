(ns battle-asserts.issues.array-interleave
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a function which interleaves an array with another array.")

(defn arguments-generator []
  (gen/tuple (gen/list gen/int) (gen/list gen/int)))

(def test-data
  [{:expected [5 "4" 1 "9"]
    :arguments [[5 1 3] ["4" "9"]]}
   {:expected [1 2 3 4 5 6]
    :arguments [[1 3 5] [2 4 6]]}
   {:expected [1 2 3 4]
    :arguments [[1 3 5] [2 4]]}
   {:expected []
    :arguments [[] [2 4 6]]}])

(defn solution [array-first array-second]
  (->>
   array-first
   (map-indexed #(vector %2 (nth array-second %1 nil)))
   flatten
   (filter #(not (nil? %)))
   (take (* 2 (min (count array-first) (count array-second))))))

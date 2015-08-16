(ns aleksandra.test.courses_test
  (:require [expectations :refer :all]
            [database.courseQuery :refer :all]
            [datomic.api :as d]))
  
(defn create-empty-in-memory-db []
  (let [uri "datomic:mem://courses-test-db"]
    (d/delete-database uri)
    (d/create-database uri)
    (let [conn (d/connect uri)
          schema (load-file "resources/datomic/schemaCourses.edn")]
      (d/transact conn schema)
      conn)))


;; Adding one course should allow us to find that course
(expect #{["Java"]}
        (with-redefs [conn (create-empty-in-memory-db)]
          (do
             (add-course "Java" "en" "12" "dfsdfds")
             (add-person-in-course "Java" "Ana") 
             (add-organization-in-course "Java" "Fon")  
             (add-duration-in-course "Java"  "12 week")
             (find-all-courses))))
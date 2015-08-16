(ns database.courseQuery
 (require [datomic.api :as d]))

;; ----- Database connection -----

(def conn nil)

;; ----- Helper functions -----
(defn find-course-id [createWork-name]
  (ffirst (d/q '[:find ?eid
                 :in $ ?createWork-name
                 :where [?eid :createWork/name ?createWork-name]]
               (d/db conn)
               createWork-name)))

;; ----- Query functions -----

(defn add-course [createWork-name createWork-inLanguage createWork-typicalAgeRange createWork-description]
  @(d/transact conn [{:db/id (d/tempid :db.part/user)
                      :createWork/name createWork-name
                   :createWork/inLanguage createWork-inLanguage
                   :createWork/typicalAgeRange createWork-typicalAgeRange
                   :createWork/description createWork-description
                   }]))

(defn add-person-in-course [createWork-name peson-name]
  (let [person-id (d/tempid :db.part/user)]
    @(d/transact conn [{:db/id person-id
                        :person/name peson-name}
                       {:db/id (find-course-id createWork-name)
                        :createWork/persons person-id}])))

(defn add-organization-in-course [createWork-name organization-name]
  (let [organization-id (d/tempid :db.part/user)]
    @(d/transact conn [{:db/id organization-id
                        :organization/name organization-name}
                       {:db/id (find-course-id createWork-name)
                        :createWork/organizations organization-id}])))

(defn add-duration-in-course [createWork-name duration-time]
  (let [duration-id (d/tempid :db.part/user)]
    @(d/transact conn [{:db/id duration-id
                        :duration/time duration-time}
                       {:db/id (find-course-id createWork-name)
                        :createWork/duration duration-id}])))

(defn find-all-courses []
  (d/q '[:find ?createWork-name
         :where [_ :createWork/name ?createWork-name]]
       (d/db conn)))





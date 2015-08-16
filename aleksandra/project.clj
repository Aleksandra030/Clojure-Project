(defproject aleksandra "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [com.datomic/datomic-free "0.9.5206" :exclusions [joda-time]]
                [expectations "2.0.9"]]
  :plugins [[lein-ring "0.8.12"]
			[lein2-eclipse "2.0.0"]
   [lein-autoexpect "1.0"]
   [lein-expectations "0.0.8"]]
  :ring {:handler aleksandra.handler/app
         :init aleksandra.handler/init
         :destroy aleksandra.handler/destroy}
  :datomic {:schemas ["resources/schema" ["my-schema.edn"
                                          "initial-data.edn"]]}
   :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:datomic {:config "resources/free-transactor-template.properties"
                        :db-uri "datomic:free://localhost:4334/my-db"}}
   :dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}
 )

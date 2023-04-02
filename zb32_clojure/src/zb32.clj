(ns zb32
    (:require [clojure.tools.cli :as cli])
    (:gen-class))


(def alphabet "ybndrfg8ejkmcpqxot1uwisza345h769")

(defn generate-zb32-string [rng length]
    (apply
        str
        (repeatedly length #(get alphabet (.nextInt rng 32)))))

(def param-spec
    [["-s" "--seed SEED" "Use a specific random seed"
      :parse-fn parse-long]
     ["-l" "--length LENGTH" "Length of each generated string"
      :parse-fn parse-long
      :default 16]
     ["-c" "--count COUNT" "Count of strings to generate"
      :parse-fn parse-long
      :default 1]
     ["-h" "--help"]])

(defn -main [& argv]
    (def args (cli/parse-opts argv param-spec))
    (def options (args :options))
    (if (options :help)
        (do
            (println "Z-Base-32 random string generator\n")
            (println "usage: zb32 [options]")
            (println (args :summary))
            (System/exit 0)))

    (def rng
        (if (options :seed)
            (new java.util.Random (options :seed))
            (new java.util.Random)))

    (dotimes [_ (options :count)]
        (println (generate-zb32-string rng (options :length))))
)

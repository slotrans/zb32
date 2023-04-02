(import spork/argparse :prefix "")

(def alphabet "ybndrfg8ejkmcpqxot1uwisza345h769")

(defn generate-zb32-string [rng length]
    (var out @[])
    (for _ 0 length
        (array/push out (get alphabet (math/rng-int rng 32))))
    (apply string/from-bytes out))


(defn main [&]
    (def args
        (argparse
            "Z-Base-32 random string generator"
             "seed" {:kind :option
                     :short "s"
                     :help "Use a specific random seed"}
             "length" {:kind :option
                       :short "l"
                       :default "16"
                       :help "Length of each generated string"}
             "count" {:kind :option
                      :short "c"
                      :default "1"
                      :help "Count of strings to generate"}))
    (unless args
        (os/exit 1))

    (def my-rng
        (if (args "seed")
            (math/rng (scan-number (args "seed")))
            (math/rng)))

    (def length-arg (scan-number (args "length")))

    (def count-arg (scan-number (args "count")))

    (for _ 0 count-arg
        (print (generate-zb32-string my-rng length-arg)))
)

(declare-project
    :name "zb32"
    :description "Z-Base-32 random string generator"
    :dependencies ["https://github.com/janet-lang/spork.git"])

(declare-executable
   :name "zb32"
   :entry "zb32.janet")

## runnning with Clojure

To compile:
`clj -M -e "(compile 'zb32)"`

To run:
`clj -M -m zb32 [options]`


## running with Babashka

`bb -m zb32 [options]`


## Notes

Needing a whole "project" structure to get this working is frustrating. I succeeded in writing one source file that runs under full-fat Clojure or under Babashka, and AOT-compiles, but lost the ability to just _run the file_. Lifting the contents of `-main` out to the top level should make it runnable as a script, but then I don't know if it can be compiled. Not that that seems worth doing, Babashka is clearly faster.

It's also disappointing that `tools.cli` doesn't generate a help/usage message for you, like `argparse` does.

The code itself is broadly similar to the Janet version, minus any mutability.

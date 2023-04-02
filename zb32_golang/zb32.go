package main

import (
    "github.com/docopt/docopt-go"
    "math/rand"
    "fmt"
    "os"
    "strings"
    "strconv"
    "time"
)

const ALPHABET = "ybndrfg8ejkmcpqxot1uwisza345h769"

func generate_zb32_string(length uint8) string {
    chars := make([]string, length)

    var i uint8 = 0
    for ; i < length; i++ {
        chars[i] = string(ALPHABET[rand.Intn(32)])
    }

    return strings.Join(chars, "")
}

func main() {
    usage := `Z-Base-32 random string generator.

Usage:
  zb32 [--seed SEED] [--length LENGTH] [--count COUNT]
  zb32 -h | --help

Options:
  -h --help           Show this screen.
  -s --seed SEED      Use a specific random seed
  -l --length LENGTH  Length of each generated string [default: 16]
  -c --count COUNT    Count of strings to generate [default: 1]`

    arguments, _ := docopt.Parse(usage, nil, true, "ZB32 1.0", false)

    if arguments["--help"].(bool) {
        fmt.Println(usage)
        os.Exit(1)
    }

    if arguments["--seed"] != nil {
        seed, _ := strconv.ParseInt(arguments["--seed"].(string), 0, 64)
        rand.Seed(seed)
    } else {
        rand.Seed(time.Now().UnixNano())
    }


    tempCount, _ := strconv.ParseInt(arguments["--count"].(string), 0, 32)
    var count int = int(tempCount)

    tempLength, _ := strconv.ParseUint(arguments["--length"].(string), 0, 8)
    var length uint8 = uint8(tempLength)

    for i := 0; i < count; i++ {
        fmt.Println(generate_zb32_string(length))
    }

    os.Exit(0)
}


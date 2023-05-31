import groovy.transform.Field
import groovy.cli.commons.CliBuilder
import java.util.Random


@Field
String ALPHABET = "ybndrfg8ejkmcpqxot1uwisza345h769"

String generateZb32String(Random rng, int length) {
    return (1..length).collect({
        ALPHABET.charAt(rng.nextInt(32))
    }).join("")
}

def cli = new CliBuilder(header: "Z-Base-32 random string generator", usage: "zb32 [options]")
cli.s(longOpt: "seed", args: 1, argName: "SEED", type: Integer.class, "Use a specific random seed")
cli.l(longOpt: "length", args: 1, argName: "LENGTH", type: Integer.class, defaultValue: "16", "Length of each generated string")
cli.c(longOpt: "count", args: 1, argName: "COUNT", type: Integer.class, defaultValue: "1", "Count of strings to generate")
cli.h(longOpt: "help", "Show this message")
def options = cli.parse(args)

if(!options || options.help) {
    cli.usage()
    System.exit(1)
}

Random rng = options.seed ? new Random(options.seed) : new Random()

(1..options.count).each {
    println(generateZb32String(rng, options.length))
}

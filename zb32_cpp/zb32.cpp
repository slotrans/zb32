#include <iostream>
#include <random>
#include <ctime>

#include "argparse.hpp" // https://raw.githubusercontent.com/p-ranav/argparse/e51655673324264dec95dd3b5168baf8e54cde17/include/argparse/argparse.hpp


constexpr std::string_view ALPHABET = "ybndrfg8ejkmcpqxot1uwisza345h769";


std::string generate_zb32_string(uint16_t length, std::default_random_engine &rng)
{
    static std::uniform_int_distribution<int> distribution(0, 32);

    std::string out_str = "";

    for(size_t i = 0; i < length; ++i)
    {
        out_str.push_back(ALPHABET[distribution(rng)]);
    }

    return out_str;
}

int main(int argc, char *argv[])
{
    std::default_random_engine rng;

    argparse::ArgumentParser args("Z-Base-32 random string generator");
    args.add_argument("--seed").scan<'i', uint64_t>().help("Use a specific random seed");
    args.add_argument("--length").scan<'i', int>().default_value(16).help("Length of each generated string");
    args.add_argument("--count").scan<'i', int>().default_value(1).help("Count of strings to generate");
    try 
    {
        args.parse_args(argc, argv);
    }
    catch(const std::runtime_error& err) 
    {
        std::cerr << err.what() << std::endl;
        std::cerr << args;
        std::exit(1);
    }

    if(auto seed = args.present<uint64_t>("--seed"))
    {
        rng.seed(*seed);
    }
    else
    {
        rng.seed(std::time(nullptr));
    }

    int length = args.get<int>("--length");
    int count = args.get<int>("--count");

    for(size_t i = 0; i < count; ++i)
    {
        std::cout << generate_zb32_string(length, rng) << std::endl;
    }

    return 0;
}

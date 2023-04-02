use rand::prelude::*;
use clap::{Command, Arg, value_parser};


const ALPHABET: [char; 32] = ['y','b','n','d','r','f','g','8','e','j','k','m','c','p','q','x','o','t','1','u','w','i','s','z','a','3','4','5','h','7','6','9'];


fn generate_zb32_string(length: u16, rng: &mut StdRng) -> String {
    let mut out_str = String::new();

    for _ in 0..length {
        let random_index: usize = rng.gen_range(0..32);
        out_str.push(ALPHABET[random_index]);
    }

    out_str
}


fn main() {   
    let args = Command::new("zb32")
        .arg(Arg::new("length")
            .long("length")
            .short('l')
            .required(false)
            .value_parser(value_parser!(u16))
            .default_value("16"))
        .arg(Arg::new("count")
            .long("count")
            .short('c')
            .required(false)
            .value_parser(value_parser!(u64))
            .default_value("1"))
        .arg(Arg::new("seed")
            .long("seed")
            .short('s')
            .required(false)
            .value_parser(value_parser!(u64)))
        .get_matches();


    let mut rng = if let Some(seed) = args.get_one::<u64>("seed").copied() {
        StdRng::seed_from_u64(seed)
    }
    else {
        StdRng::from_entropy()
    };

    let count = args.get_one::<u64>("count").copied().expect("should have a default value");

    let length = args.get_one::<u16>("length").copied().expect("should have a default value");


    for _ in 0..count {
        let zb32_string = generate_zb32_string(length, &mut rng);
        println!("{zb32_string}");
    }
}

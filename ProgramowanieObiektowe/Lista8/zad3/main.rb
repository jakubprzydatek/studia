require_relative "Jawna"
require_relative "Zaszyfrowane"

klucz = {" " => " ", "a" => "b", "b" => "c", "c" => "d", "d" => "e",
         "e" => "f", "f" => "g", "g" => "h", "h" => "i", "i" => "j",
         "j" => "k", "k" => "l", "l" => "m", "m" => "n", "n" => "o",
         "o" => "p", "p" => "r", "r" => "s", "s" => "t", "t" => "u",
         "u" => "v", "v" => "w", "w" => "x", "x" => "y", "y" => "z", "z" => "a"}

napis = Jawna.new("ruby")
szyfr = napis.zaszyfruj(klucz)
puts(napis.to_s)
puts(szyfr.to_s)

napis = szyfr.odszyfruj(klucz)
puts(napis.to_s)
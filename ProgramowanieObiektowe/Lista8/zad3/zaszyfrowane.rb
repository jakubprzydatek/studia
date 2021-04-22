require_relative "Jawna"

class Zaszyfrowane
  def initialize(napis)
    @napis = napis
  end

  def to_s
    @napis
  end

  def odszyfruj(klucz)
    klucz = klucz.invert
    d = @napis.length
    i = 0
    wynik = ""
    while i < d
      wynik += klucz[@napis[i]]
      i+=1
    end
    szyfr = Jawna.new(wynik)
  end
end
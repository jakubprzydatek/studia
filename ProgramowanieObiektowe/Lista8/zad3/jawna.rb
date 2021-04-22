require_relative "Zaszyfrowane"
class Jawna

  def initialize(napis)
    @napis = napis
  end

  def to_s
    @napis
  end

  def zaszyfruj(klucz)
    d = @napis.length
    i = 0
    wynik = ""
    while i < d
      wynik += klucz[@napis[i]]
      i+=1
    end
    szyfr = Zaszyfrowane.new(wynik)
  end
end
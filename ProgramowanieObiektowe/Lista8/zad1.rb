
class Integer
  def czynniki
    tablica = Array.new
    i = 1
    while i <= self
      if self % i == 0
        tablica.push(i)
      end
      i += 1
    end
    tablica
  end

  def ack(y)
    if self == 0
      y + 1
    elsif y == 0
      (self-1).ack(1)
    else
      (self-1).ack(self.ack(y-1))
    end
  end

  def doskonala
    dzielniki = self.czynniki
    suma = dzielniki.inject(0) { |sum, x| sum + x } - self
    if suma == self
      true
    else
      false
    end
  end

  def slownie
    def liczebnik(n)
      case
      when n == 1 then "jeden"
      when n == 2 then "dwa"
      when n == 3 then "trzy"
      when n == 4 then "cztery"
      when n == 5 then "piec"
      when n == 6 then "szesc"
      when n == 7 then "siedem"
      when n == 8 then "osiem"
      when n == 9 then "dziewiec"
      else "zero"
      end
    end

    i = 0
    slowa = Array.new
    liczba = self
    while liczba > 0
      slowa.unshift(liczebnik(liczba%10))
      liczba = liczba/10
    end
    slowa
  end
end


puts("Testy czynniki dla 6 oraz 1230")
puts("Dla 6:")
puts(6.czynniki)
puts(" ")
puts("Dla 1230:")
puts(1230.czynniki)

puts("Testy ack dla 2 i argumentu 1")
puts(2.ack(1))
puts(" ")

puts("Czy doskonała dla 28 i 5")
puts("28:")
puts(28.doskonala)
puts("5:")
puts(5.doskonala)

puts("Slownie 1000 i 2556")
puts(" ")
puts("1000 ")
puts(1000.slownie)
puts(" ")
puts("2556 ")
puts(2556.slownie)
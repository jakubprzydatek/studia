require 'gnuplotrb'
include GnuplotRB

=begin
**********************************************************************
  Uzywam IDE RubyMine na Windows. Mam nadzieje ze wersja gnuplot ktorej
  uzywam bedzie kompatybilna i pozwoli sie skompilowac. Jesli bylyby jakies
  problemy przy sprawdzaniu mojego zadania, prosze o informacje.
  W Gemfile jest kod dzieki ktoremu moglem uzyc gnuplot na windows i podpiac
  je pod moj program.
**********************************************************************
=end

class Funkcja
  def initialize(funkcja, nazwafunkcji)
    @funkcja = funkcja
    @nazwafunkcji = nazwafunkcji
  end

  def value(arg)
    @funkcja.call(arg)
  end

  def zerowe(a, b, e)
    i = e.to_s.length
    while a <= b
      puts(a) if @funkcja.call(a) == 0
      a += e
      a = a.round(i)
    end
  end

  def pole(a, b)
    pole = 0
    while a <= b
      pole += 0.0001 * @funkcja.call(a)
      a += 0.0001
    end
    puts(pole)
  end

  def poch(x)
    wartosc = (@funkcja.call(x + 0.00001) - @funkcja.call(x)) / 0.00001
    puts(wartosc)
  end

  def rysuj(startoffunction, endoffunction)

    x = (startoffunction..endoffunction).step(0.01).to_a
    y = x.map { |val| value(val) }

    plot = Plot.new(
      [[x, y], notitle: true],
      polar: false
)

    plot.to_png('./wykres' + @nazwafunkcji + '.png', size: [600, 600])
    puts('Narysowano wykres funkcji. Nazwa pliku to ' + './wykres' + @nazwafunkcji + '.png')
    puts(' ')
  end
end

puts('Funkcja n * n - 2 * n - 8')
funkcja = Funkcja.new(->(n) { n * n - 2 * n - 8 }, 'funkcja')
puts('Wartosc funkcji: ' + funkcja.value(10).to_s)
puts('Miejsca zerowe [-5:5]')
funkcja.zerowe(-5, 5, 1)
puts('Pole pod wykresem na przedziale [0:5]')
funkcja.pole(0, 5)
puts('Pochodna x=10')
funkcja.poch(10)
funkcja.rysuj(-10, 10)

puts('Funkcja1 n * n * n + 5 * n + 6')
funkcja1 = Funkcja.new(->(n) { n * n * n + 5 * n + 6 }, 'funkcja1')
puts('Wartosc funkcji1: ' + funkcja1.value(10).to_s)
puts('Miejsca zerowe [-10:10]')
funkcja1.zerowe(-10, 10, 0.1)
puts('Pole pod wykresem na przedziale [0:5]')
funkcja1.pole(0, 5)
puts('Pochodna x=10')
funkcja1.poch(10)
funkcja1.rysuj(-10, 10)

puts('Funkcja2 sin(n)')
funkcja2 = Funkcja.new(->(n) { Math.sin(n) }, 'funkcja2')
puts('Wartosc funkcji2: ' + funkcja2.value(10).to_s)
puts('Miejsca zerowe [-5:5], ze wzgledu na ilosc miejsc po przecinku liczby PI znajdziemy tylko 0.0')
funkcja2.zerowe(-5, 5, 0.0001)
puts('Pole pod wykresem na przedziale [0:5]')
funkcja2.pole(0, 5)
puts('Pochodna x=10')
funkcja2.poch(10)
funkcja2.rysuj(-10, 10)

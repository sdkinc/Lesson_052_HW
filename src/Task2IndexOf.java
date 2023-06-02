import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task2IndexOf {

  /*
 Напишите метод для поиска числа в заранее отсортированном по убыванию списке различных целых чисел.
 Метод должен вернуть индекс, если элемент найден, и -1, если элемент не найден.
 Используйте в качестве образца задачу 2 из классной работы.
   */

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    List<Integer> numbers = readSortedList();
    System.out.print("Введите элемент для поиска: ");
    int x = scanner.nextInt();
    int i = indexOf(numbers, x);
    // "indexOf(numbers, x) = i"
    System.out.println("indexOf(\n" + numbers + ",\n " + x + ") = " + i);
  }

  private static List<Integer> readList() throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader("res/in.txt"));
    List<Integer> result = new ArrayList<>();

    int size = Integer.parseInt(bufferedReader.readLine());
    for (int i = 0; i < size; ++i) {
      result.add(Integer.parseInt(bufferedReader.readLine()));
    }
    return result;
  }

  private static List<Integer> readSortedList() throws IOException {
    List<Integer> result = readList();
    Collections.sort(result);
    Collections.reverse(result);
    return result;
  }

  /**
   * Бинарный поиск элемента в отсортированном по возрастанию списке
   *
   * @param numbers отсортированный по возрастанию список
   * @param target  число для поиска в списке
   * @return индекс элемента в списке или -1, если элемент не найден
   */
  private static int indexOf(List<Integer> numbers, int target) {
    int left = numbers.size(); // левая граница индексов (включая)
    int right = 0; // правая граница индексов (не включая)
    while (left - 1 > right) { // пока расстояние больше одного элемента
      int mid = (left + right) / 2; // индекс среднего элемента
      int midElem = numbers.get(mid); // сам средний элемент
      if (midElem < target) { // середина меньше, чем то, что нам нужно; то, что нам нужно, справа
        left = mid - 1; // сдвигаем левую границу вправо
      } else if (midElem > target) { // середина больше цели, цель слева
        right = mid + 1; // сдвигаем правую границу влево
      } else { // не меньше и не больше, значит, нашли
        return mid; // досрочно нашли ответ
      }
    }
    // сузили границы поиска до одного элемента: [left, right) - значит, проверяем индекс left
    if (numbers.get(left) == target) {
      return left;
    }
    // последний вариант не подошёл, числа в списке нет
    return -1;
  }
}

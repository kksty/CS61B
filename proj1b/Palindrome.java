public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> words = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            words.addLast(word.charAt(i));
        }
        return words;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordList = wordToDeque(word);
        int size = wordList.size() / 2;
        for (int i = 0; i < size; i++) {
            if (!wordList.removeFirst().equals(wordList.removeLast())) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordList = wordToDeque(word);
        int size = wordList.size() / 2;
        for (int i = 0; i < size; i++) {
            if (!cc.equalChars(wordList.removeFirst(), wordList.removeLast())) {
                return false;
            }
        }
        return true;
    }
}

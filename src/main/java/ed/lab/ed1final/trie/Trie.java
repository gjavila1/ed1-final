package ed.lab.ed1final.trie;

import org.springframework.stereotype.Component;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//Con este metodo declaramos el Nodo Trie
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    int wordCount = 0;
    int prefixCount = 0;
}

@Component
public class Trie {
    private final TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    //Metodo para insertar una palabra en el trie,...
    public void insert(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
          node = node.children.computeIfAbsent(c,k -> new TrieNode());
          node.prefixCount++;
        }
        node.wordCount++;
    }
    //Metodo para contar cuantas veces se ha insertado exactamente una palabra en el Trie
    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            node = node.children.get(c);
            if(node == null) {
                return 0;
            }
        }
        return node.wordCount;
    }

    //Metodo para cuantas palabras empiezan con el prefijo (Se puede usarla misma logica que el metodo anterior)
    //Retornando el valor de "PrefixCount"
    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if(node == null) {
                return 0;
            }
        }
        return node.prefixCount;
    }
    //Eliminar el Trie si es que existe
    public void erase(String word) {
        if (countWordsEqualTo(word) == 0) return;

        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode next = current.children.get(ch);
            next.prefixCount--;
            current = next;
        }
        current.wordCount--;
    }
}



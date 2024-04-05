import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HuffmanCoder {
    HashMap<Character, String> encoder;
    HashMap<String, Character> decoder;

    private class Node implements Comparable<Node> {
        Character data;
        int cost;
        Node left;
        Node right;

        public Node(Character data, int cost) {
            this.data = data;
            this.cost = cost;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    public HuffmanCoder(String inputFile) throws Exception {
        String text = readTextFile(inputFile);
        HashMap<Character, Integer> fmap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char cc = text.charAt(i);
            if (fmap.containsKey(cc)) {
                int ov = fmap.get(cc);
                ov += 1;
                fmap.put(cc, ov);
            } else {
                fmap.put(cc, 1);
            }
        }

        Heap<Node> minHeap = new Heap<>();
        for (Map.Entry<Character, Integer> entry : fmap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            minHeap.insert(node);
        }
        while (minHeap.size() != 1) {
            Node first = minHeap.remove();
            Node second = minHeap.remove();
            Node newNode = new Node('\0', first.cost + second.cost);
            newNode.left = first;
            newNode.right = second;
            minHeap.insert(newNode);
        }

        Node ft = minHeap.remove();
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();
        this.initEncoderDecoder(ft, "");
    }

    private void initEncoderDecoder(Node node, String osf) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            this.encoder.put(node.data, osf);
            this.decoder.put(osf, node.data);
        }
        initEncoderDecoder(node.left, osf + "0");
        initEncoderDecoder(node.right, osf + "1");
    }

    public void compress(String inputFile, String outputFile) throws IOException {
        String text = readTextFile(inputFile);
        String compressedText = encode(text);
        writeTextFile(outputFile, compressedText);
    }

    public void decompress(String inputFile, String outputFile) throws IOException {
        String compressedText = readTextFile(inputFile);
        String decompressedText = decode(compressedText);
        writeTextFile(outputFile, decompressedText);
    }

    private String readTextFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private void writeTextFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }

    public String encode(String source) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            ans.append(encoder.get(source.charAt(i)));
        }
        return ans.toString();
    }

    public String decode(String codedString) {
        String key = "";
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < codedString.length(); i++) {
            key = key + codedString.charAt(i);
            if (decoder.containsKey(key)) {
                ans.append(decoder.get(key));
                key = "";
            }
        }
        return ans.toString();
    }
    
    

    public static void main(String[] args) {
        String inputFile = "C:\\Users\\balaraju\\OneDrive\\Desktop\\java_dsa\\huffman\\input.txt";
        String outputFile = "compressed.txt";
        try {
            HuffmanCoder hf = new HuffmanCoder(inputFile);
            hf.compress(inputFile, outputFile);
            System.out.println("Compression completed. Compressed file: " + outputFile);
    
            String decompressedOutputFile = "decompressed.txt";
            hf.decompress(outputFile, decompressedOutputFile);
            System.out.println("Decompression completed. Decompressed file: " + decompressedOutputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

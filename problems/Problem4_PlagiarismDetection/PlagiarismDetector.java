import java.util.*;

public class PlagiarismDetector {

    // n-gram -> set of document IDs
    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();

    private int N = 5; // 5-gram


    // Add document to system
    public void addDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        for (String gram : ngrams) {

            ngramIndex.putIfAbsent(gram, new HashSet<>());
            ngramIndex.get(gram).add(docId);
        }
    }


    // Generate n-grams
    private List<String> generateNGrams(String text) {

        String[] words = text.split("\\s+");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < N; j++) {
                sb.append(words[i + j]).append(" ");
            }

            ngrams.add(sb.toString().trim());
        }

        return ngrams;
    }


    // Analyze new document
    public void analyzeDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String gram : ngrams) {

            if (ngramIndex.containsKey(gram)) {

                for (String existingDoc : ngramIndex.get(gram)) {

                    matchCount.put(existingDoc,
                            matchCount.getOrDefault(existingDoc, 0) + 1);
                }
            }
        }

        // Print similarity
        for (String doc : matchCount.keySet()) {

            int matches = matchCount.get(doc);
            double similarity = (matches * 100.0) / ngrams.size();

            System.out.println("Matched with " + doc +
                    " → " + String.format("%.2f", similarity) + "%");

            if (similarity > 50) {
                System.out.println("⚠️ PLAGIARISM DETECTED with " + doc);
            }
        }
    }


    // Demo
    public static void main(String[] args) {

        PlagiarismDetector pd = new PlagiarismDetector();

        String doc1 = "this is a simple example of plagiarism detection system using hash maps";
        String doc2 = "this is a simple example of plagiarism detection system using advanced methods";

        pd.addDocument("doc1", doc1);

        pd.analyzeDocument("doc2", doc2);
    }
}
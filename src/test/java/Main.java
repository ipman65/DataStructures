import dataStructures.*;

class Main{
    public static void main(String[] args) {
        System.out.println("MyHashTableTest: ");
        MyHashTableTest("martin");
    }

    private static void MyHashTableTest(String testData){
        MyHashTable<Integer, Character> myHashTable = new MyHashTable<>();

        for(int i = 0; i < testData.length(); i++){
            myHashTable.put(i, testData.charAt(i));
            assertEquals(myHashTable.get(i), testData.charAt(i));
        }

        for (MyHashTable.Entry<Integer, Character> e : myHashTable){
            System.out.print(e.getValue());
        }

    }

    private static <T> boolean assertEquals(T actual, T expected){
        System.out.println("expected: " + expected
                + "\nactual: " + actual);
        return actual == expected;
    }
}
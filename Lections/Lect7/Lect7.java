package Lect7;

public class Lect7 {
    public static void main(String[] args) {

        Map<String, Student> = new HashMap<>();

        map.put("Id1", new Student("name1", 1));
        map.put("Id2", new Student("name1", 1));
        map.put("Id3", new Student("name1", 1));
        map.put("Id4", new Student("name1", 1));

        Student = map.get("Id3");
        
        String key ="Id5";
        if(map.contains(key)){
            map.get(key);
        }
        Student = map.remove(key);

        for(Map.Entry<String, Student> entry : map.entrySet()){
            
        }

    }
}

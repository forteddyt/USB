import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Regex2 {
    public static String[] getGuestNames(String[] names) {
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < names.length; i++)
            if (names[i].matches("(\\w+,?) (\\w+,?) ?(\\w+,?)"))
                temp.add(names[i]);

        String[] ans = new String[temp.size()];
        int c = 0;

        for (String s : temp) {
            if (s.matches("\\w+, \\w+"))
                ans[c] = s;
            else if (s.matches("\\w+ \\w+")) {
                String[] q = s.split(" ");
                ans[c] = q[1] + ", " + q[0];
            } else if (s.matches("\\w+ \\w+ \\w+")) {
                String[] q = s.split(" ");
                ans[c] = q[2] + ", " + q[0] + " " + q[1];
            }
            c++;
        }
        Arrays.sort(ans);
        return ans;
    }
}
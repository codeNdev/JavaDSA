package leetcodedaily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveSubFoldersFromFilesystem {
    public List<String> removeSubfolders(String[] folder) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(folder);
        for (String f : folder) {
            if (ans.isEmpty() || !f.startsWith(ans.get(ans.size() - 1) + "/")) {
                ans.add(f);
            }
        }
        return ans;
    }
}

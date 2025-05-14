import java.util.*;

class Solution {
    static class Music {
        int id;
        String genre;
        int play;
        public Music(int id, String genre, int play) {
            this.id = id;
            this.genre = genre;
            this.play = play;
        }
    }
    public List<Integer> solution(String[] genres, int[] plays) {
        Music[] playList = new Music[genres.length];
        Set<String> genreList = new HashSet<>();
        
        for (int i=0; i<playList.length; i++) {
            playList[i] = new Music(i, genres[i], plays[i]);
            genreList.add(genres[i]);
        }
        
        List<Integer> answer = new ArrayList<>();
        Music[] playCnts = new Music[genreList.size()];
        int p = 0;
        for (String g : genreList) {
            int playCnt = 0;
            for (int i=0; i<playList.length; i++) {
                if (playList[i].genre.equals(g)) {
                    playCnt += playList[i].play;
                }
            }
            playCnts[p++] = new Music(0, g, playCnt);
        }
        
        Arrays.sort(playCnts, (a, b) -> {
            return b.play - a.play;
        });
        
        int idx = 0;
        for (Music pm : playCnts) {
            List<int[]> play_ = new ArrayList<>();
            for (Music pl : playList) {
                if (pl.genre.equals(pm.genre)) {
                    play_.add(new int[] {pl.id, pl.play});
                }
            }
            Collections.sort(play_, (a, b) -> {
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                return b[1] - a[1];
            });
            answer.add(play_.get(0)[0]);
            if (play_.size() > 1) {
                answer.add(play_.get(1)[0]);
            }
        }
        return answer;
    }
}
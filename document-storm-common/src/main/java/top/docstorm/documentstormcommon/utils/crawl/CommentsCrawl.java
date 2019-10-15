package top.docstorm.documentstormcommon.utils.crawl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import top.docstorm.documentstormcommon.domain.Comment;
import top.docstorm.documentstormcommon.domain.Comments;
import top.docstorm.documentstormcommon.domain.PlayLists;
import top.docstorm.documentstormcommon.domain.Song;

import java.net.URL;
import java.util.*;

/**
* @Description: 网易云评论爬取工具类<p>
* @author: passer<p>
* @version：2019年5月20日 下午8:27:02<p>
*/
public class CommentsCrawl {
	private static final String IP = "http://47.100.45.234";
	private static final String CRAWLABLE_LIST_URL = "/top/list?idx=";
	private static final String COMMENT_CRAWL_URL = "/comment/hot?type=0&id=";
	private static final int MAX_LIST_ID = 23;
	private static final int INIT_LIST_ID = -1;
	private static int listId = INIT_LIST_ID;

	private static final LinkedList<Comment> commentSet = new LinkedList<>();
			
	private static final Map<Comment, Song> map = new HashMap<>();
	public synchronized static Comment getComment() throws Exception {
		if (commentSet.size() == 0) {
			listId++;
			URL listUrl = new URL(IP + CRAWLABLE_LIST_URL + listId);
			PlayLists playLists = JSON.parseObject(IOUtils.toString(listUrl), PlayLists.class);
			List<Song> tracks = playLists.getPlaylist().getTracks();
			for (Song song : tracks) {
				URL url = new URL(IP + COMMENT_CRAWL_URL + song.getId());
				Comments comments = JSON.parseObject(IOUtils.toString(url), Comments.class);
				comments.setSong(song);
				commentSet.addAll(comments.getHotComments());
				for (Comment comment : comments.getHotComments()) {
					map.put(comment, song);
				}
			}
			if (listId == MAX_LIST_ID)
				listId = INIT_LIST_ID;
		}
		Random random = new Random();
		int randomNum = random.nextInt(commentSet.size());
		Comment comment = commentSet.remove(randomNum);
		comment.setSong(map.remove(comment));
		return comment;
	}
}

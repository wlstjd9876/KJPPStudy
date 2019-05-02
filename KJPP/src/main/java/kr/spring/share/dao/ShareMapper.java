package kr.spring.share.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.share.domain.ShareCommand;
import kr.spring.share.domain.ShareReplyCommand;

public interface ShareMapper {
   //부모글
   public List<ShareCommand> selectList(Map<String, Object> map);
   public int selectRowCount(Map< String, Object> map);
   @Insert("INSERT INTO share_write (num,title,content,reg_date,email,thumb,photo2,photo3,s_num) " //uploadfile는 바이트 배열, upload는 MultipartFile이므로 명시하지 않음★
         + "VALUES (share_write_seq.nextval,#{title},#{content},SYSDATE,#{email},#{thumb},#{photo2},#{photo3},#{s_num})")
   public void insert(ShareCommand share);
   @Select("SELECT * FROM share_write s JOIN t_member_detail t ON s.email=t.email WHERE num=#{num}")
   public ShareCommand selectShare(Integer num); //MyBatis에 데이터를 넘기기 위해 Integer로 사용
   public void update(ShareCommand share);
   @Delete("DELETE FROM share_write WHERE num=#{num}")
   public void delete(Integer num);
   
   //댓글
   public List<ShareReplyCommand> selectListReply(Map<String, Object> map);
   @Select("SELECT COUNT(*) FROM share_reply WHERE num=#{num}")
   public int selectRowCountReply(Map<String, Object> map);
   @Insert("INSERT INTO share_reply (sr_num,sr_content,sr_date,email,num) VALUES (share_reply_seq.nextval,#{sr_content},SYSDATE,#{email},#{num})")
   public void insertReply(ShareReplyCommand shareReply);
   @Update("UPDATE share_reply SET sr_content=#{sr_content} WHERE sr_num=#{sr_num}")
   public void updateReply(ShareReplyCommand shareReply);
   @Delete("DELETE FROM share_reply WHERE sr_num=#{sr_num}")
   public void deleteReply(Integer sr_num);
   //부모글 삭제시 댓글이 존재하면 부모글 삭제 전 댓글 삭제 (부모글에 달린 댓글 삭제)
   @Delete("DELETE FROM share_reply WHERE num=#{num}")
   public void deleteReplyByNum(Integer num);
}
package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPost(int userId, int offset, int limit);

    // Param 注解用于给参数取别名
    // 如果只有一个参数，并且在 <if> 里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

    /**
     * 增加贴子
     * @param discussPost 要插入的DiscussPost对象
     * @return
     */
    int insertDiscussPost(DiscussPost discussPost);

    /**
     *  查询贴子
     * @param id 用户id
     * @return
     */
    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

    /**
     * 修改帖子类型
     */
    int updateType(int id, int type);

    /**
     * 修改帖子状态
     */
    int updateStatus(int id, int status);

}

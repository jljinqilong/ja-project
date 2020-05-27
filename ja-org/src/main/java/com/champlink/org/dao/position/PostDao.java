package com.champlink.org.dao.position;

import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.org.position.Position;
import com.champlink.common.domain.org.position.Post;
import com.github.pagehelper.Page;

@Mapper
public interface PostDao {

	int add(Post post);

	int delById(Long userId);

	int update(Post post);

	Position getById(Long rowId);

	Page<Post> pageList1(Paginater paginater);

}
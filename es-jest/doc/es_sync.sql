#explain
select 
	# t_article_nation
	artan.id id,artan.id articleNationId,artan.article_id articleId,artan.nation_id nationId,
	artan.del articleNationDel,DATE_FORMAT(artan.update_time, '%Y-%c-%d %H:%i:%s') articleNationUpdateTime,
	artan.name articleNationName,artan.content articleNationContent,
	DATE_FORMAT(artan.create_time, '%Y-%c-%d %H:%i:%s') articleNationCreateTime
	# t_article
	art.site_id siteId,
	# t_nation
	nat.code nationCode,
	# t_category
	cate.id categoryId,catean.name categoryName,catean.del categoryDel,
	# t_sections
	sec.id sectionsId,secan.name sectionsName,secan.del sectionsDel,
	# t_site
	si.domain domain
from t_article_nation artan
	left join t_article art
		on artan.article_id=art.id
	left join t_site si
		on art.site_id = si.id
	left join t_nation nat
		on artan.nation_id=nat.id
	left join t_sections sec
		on art.section_id=sec.id
	left join t_section_nation secan
		on art.section_id = secan.section_id
	left join t_category cate
		on art.cate_id=cate.id
	left join t_category_nation catean
		on art.cate_id = catean.cate_id
where artan.update_time > :sql_last_value


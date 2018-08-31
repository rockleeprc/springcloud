#explain
select 
	# t_article_nation
	artan.id articleNationId,artan.article_id articleId,artan.nation_id nationId,
	artan.del articleNationDel,artan.update_time articleNationUpdateTime,
	artan.name articleNationName,artan.content articleNationContent,
	# t_article
	art.site_id siteId,
	# t_nation
	nat.code nationCode,
	# t_category
	cate.id categoryId,cate.name categoryName,cate.del categoryDel,
	# t_sections
	sec.id sectionsId,sec.name sectionsName,sec.del sectionsDel,
	# t_site
	si.name siteName
from t_article_nation artan
	left join t_article art 
		on artan.article_id=art.id
	left join t_nation nat 
		on artan.nation_id=nat.id
	left join t_category cate
		on art.cate_id=cate.id
	left join t_sections sec 
		on art.section_id=sec.id
	left join t_site si
		on art.site_id = si.id

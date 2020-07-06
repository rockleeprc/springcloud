package com.foo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foo.bean.Person;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class JsonTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // FAIL_ON_UNKNOWN_PROPERTIES在序列化的时候，如果遇到不认识的字段的处理方式
        // 默认启用特性，这意味着在遇到未知属性时抛出JsonMappingException。在引入该特性之前，这是默认的默认设置。
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 过滤类的属性id
        // OBJECT_MAPPER.setFilters(new
        // SimpleFilterProvider().setFailOnUnknownId(false));
        // 在序列化时，只有那些值为null或被认为为空的值的属性才不会被包含在内。
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    @Test
    public void t1() throws JsonProcessingException {
        String json = "{\"data\":[{\"id\":\"1143760799005937665\",\"cus_id\":\"1143760799005937664\",\"source\":\"2\",\"source2\":null,\"source_type\":\"6\",\"type\":\"1\",\"category\":\"2\",\"message_time\":\"2019-06-26 13:59:23\",\"message_point\":\"留言重点\",\"assign_id\":null,\"assign_time\":null,\"is_assign\":null,\"assign_fail_reason\":null,\"repeat_status\":\"0\",\"search_word\":\"搜索词\",\"key_word\":null,\"input_type\":\"1\",\"phase\":\"4\",\"status\":null,\"sea_level\":null,\"release_date\":null,\"industry_category\":null,\"focus_point\":null,\"has_invite\":null,\"customer_status\":\"1\",\"is_transfer\":\"0\",\"is_sign\":\"0\",\"tel_end_time\":null,\"fow_end_time\":null,\"create_user\":\"1134063909611900928\",\"create_time\":\"2019-06-26 13:59:59\",\"update_user\":\"1134063909611900928\",\"update_time\":\"2019-06-26 14:02:40\",\"visit_num\":null,\"visit_status\":\"-1\",\"not_visit_reason\":null,\"release_num\":null,\"cust_status_update_time\":\"2019-06-26 13:59:59\",\"exchange_num\":\"0\",\"is_repeat_phone\":\"0\",\"abandon_reason\":null,\"phTra_assign_time\":null,\"phTra_update_Time\":null,\"phTra_focus_point\":null,\"phTra_end_time\":null,\"phTra_customer_status\":null,\"abandon_time\":null,\"phstatus\":null,\"next_visit_time\":null,\"reserve_time\":null,\"remark\":null,\"is_recall\":\"0\",\"recall_time\":null,\"promotion_status\":null,\"pvid\":null,\"source_from\":null,\"is_tail_money\":\"0\",\"import_time\":null,\"business_line\":\"2\",\"is_send\":\"0\",\"is_call\":null,\"first_call\":null,\"com_pool_show_flag\":\"1\",\"first_call_time\":null,\"phtra_is_call\":null,\"phtra_first_call\":null,\"is_invite_letter\":null,\"phtra_assign_dir_time\":null}],\"database\":\"aggregation\",\"es\":1561529350000,\"id\":677,\"isDdl\":false,\"mysqlType\":{\"id\":\"bigint(20)\",\"cus_id\":\"bigint(20)\",\"source\":\"int\",\"source2\":\"tinyint(4)\",\"source_type\":\"tinyint(4)\",\"type\":\"tinyint(4)\",\"category\":\"tinyint(4)\",\"message_time\":\"datetime\",\"message_point\":\"varchar(1500)\",\"assign_id\":\"bigint(20)\",\"assign_time\":\"datetime\",\"is_assign\":\"tinyint(4)\",\"assign_fail_reason\":\"varchar(20)\",\"repeat_status\":\"tinyint(4)\",\"search_word\":\"varchar(500)\",\"key_word\":\"varchar(50)\",\"input_type\":\"tinyint(4)\",\"phase\":\"tinyint(4)\",\"status\":\"tinyint(4)\",\"sea_level\":\"char(10)\",\"release_date\":\"datetime\",\"industry_category\":\"int(11)\",\"focus_point\":\"varchar(1000)\",\"has_invite\":\"tinyint(4)\",\"customer_status\":\"tinyint(4)\",\"is_transfer\":\"tinyint(4)\",\"is_sign\":\"tinyint(4)\",\"tel_end_time\":\"datetime\",\"fow_end_time\":\"datetime\",\"create_user\":\"bigint(20)\",\"create_time\":\"datetime\",\"update_user\":\"bigint(20)\",\"update_time\":\"datetime\",\"visit_num\":\"tinyint(4)\",\"visit_status\":\"tinyint(4)\",\"not_visit_reason\":\"varchar(100)\",\"release_num\":\"tinyint(4)\",\"cust_status_update_time\":\"datetime\",\"exchange_num\":\"int(11)\",\"is_repeat_phone\":\"tinyint(3)\",\"abandon_reason\":\"tinyint(4)\",\"phTra_assign_time\":\"datetime\",\"phTra_update_Time\":\"datetime\",\"phTra_focus_point\":\"varchar(500)\",\"phTra_end_time\":\"datetime\",\"phTra_customer_status\":\"tinyint(4)\",\"abandon_time\":\"datetime\",\"phstatus\":\"tinyint(4)\",\"next_visit_time\":\"datetime\",\"reserve_time\":\"datetime\",\"remark\":\"varchar(255)\",\"is_recall\":\"tinyint(4)\",\"recall_time\":\"datetime\",\"promotion_status\":\"tinyint(4)\",\"pvid\":\"varchar(64)\",\"source_from\":\"int(11)\",\"is_tail_money\":\"tinyint(4)\",\"import_time\":\"datetime\",\"business_line\":\"tinyint(4)\",\"is_send\":\"tinyint(3)\",\"is_call\":\"tinyint(4)\",\"first_call\":\"tinyint(4)\",\"com_pool_show_flag\":\"tinyint(4)\",\"first_call_time\":\"datetime\",\"phtra_is_call\":\"tinyint(4)\",\"phtra_first_call\":\"tinyint(4)\",\"is_invite_letter\":\"tinyint(4)\",\"phtra_assign_dir_time\":\"datetime\"},\"old\":[{\"exchange_num\":null}],\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"id\":-5,\"cus_id\":-5,\"source\":4,\"source2\":-6,\"source_type\":-6,\"type\":-6,\"category\":-6,\"message_time\":93,\"message_point\":12,\"assign_id\":-5,\"assign_time\":93,\"is_assign\":-6,\"assign_fail_reason\":12,\"repeat_status\":-6,\"search_word\":12,\"key_word\":12,\"input_type\":-6,\"phase\":-6,\"status\":-6,\"sea_level\":1,\"release_date\":93,\"industry_category\":4,\"focus_point\":12,\"has_invite\":-6,\"customer_status\":-6,\"is_transfer\":-6,\"is_sign\":-6,\"tel_end_time\":93,\"fow_end_time\":93,\"create_user\":-5,\"create_time\":93,\"update_user\":-5,\"update_time\":93,\"visit_num\":-6,\"visit_status\":-6,\"not_visit_reason\":12,\"release_num\":-6,\"cust_status_update_time\":93,\"exchange_num\":4,\"is_repeat_phone\":-6,\"abandon_reason\":-6,\"phTra_assign_time\":93,\"phTra_update_Time\":93,\"phTra_focus_point\":12,\"phTra_end_time\":93,\"phTra_customer_status\":-6,\"abandon_time\":93,\"phstatus\":-6,\"next_visit_time\":93,\"reserve_time\":93,\"remark\":12,\"is_recall\":-6,\"recall_time\":93,\"promotion_status\":-6,\"pvid\":12,\"source_from\":4,\"is_tail_money\":-6,\"import_time\":93,\"business_line\":-6,\"is_send\":-6,\"is_call\":-6,\"first_call\":-6,\"com_pool_show_flag\":-6,\"first_call_time\":93,\"phtra_is_call\":-6,\"phtra_first_call\":-6,\"is_invite_letter\":-6,\"phtra_assign_dir_time\":93},\"table\":\"t_clue_basic\",\"ts\":1561529350428,\"type\":\"UPDATE\"}";
        JsonNode jsonNode = mapper.readValue(json, JsonNode.class);

        JsonNode table = jsonNode.get("table");
        System.out.println(table.asText());
    }

    @Test
    public void t2() throws JsonProcessingException {
        Person p1 = new Person("jjjj", 10);
        String json = mapper.writeValueAsString(p1);
        Person p2 = mapper.readValue(json, Person.class);
        System.out.println(p2);

    }
}

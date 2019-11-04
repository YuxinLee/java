package com.example.demo.repository;

import com.example.demo.model.Deparment;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * 自定义方法：
 * 1. (CREATE)直接通过方法的命名規則去解析
 * 2. (USE_DECLARED_QUERY)通过Query去解析
 * 3. (CREATE_IF_NOT_FOUND)默认策略：优先查詢是否有定义好的查询语句，如果没有，就跟据方法的名字去构建查询。
 */
public interface UserRepository extends JpaRepository<User, Long> {

    //条件查询
    List<User> findByCreateDateAndAndName(Date createDate, String name);

    //Distinct查询
    List<User> findDistinctNameByCreateDateOrDeparment(Date createDate, Deparment deparment);
    List<User> findNameDistinctByCreateDateOrDeparment(Date createDate, Deparment deparment);

    //使用IgnoreCase来忽略大小写
    List<User> findByNameIgnoreCase(String name);
    List<User> findByNameAndLastnameAllIgnoreCase(String name, String lastName);

    //使用OrderBy来排序
    List<User> findByLastnameOrderByFirstnameAsc(String lastname);
    List<User> findByLastnameOrderByFirstnameDesc(String lastname);

    //findByAddressZipCode:根据Address对象中的ZipCode属性来查询User，第一次匹配的
    //时候，会分割为AddressZip和Code，如果不匹配会向左移动分割点，分割为Address和ZipCode
    //为了避免这种情况，可以使用_进行区分，findByAddress_ZipCode
    List<User> findByAddressDeparment(Deparment deparment);
}

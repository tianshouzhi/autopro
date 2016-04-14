<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE sqlMap PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd" >
<sqlMap namespace="t_uud_order_compare" >
  <resultMap id="OrderCompareResult" class="cn._189.e.apipay.entity.OrderCompare" >
    <result column="id" property="id" jdbcType="INTEGER" />
    <result column="curr_date" property="currDate" jdbcType="DATE" />
    <result column="local_total_amount" property="localTotalAmount" jdbcType="INTEGER" />
    <result column="local_total_row" property="localTotalRow" jdbcType="INTEGER" />
    <result column="total_amount" property="totalAmount" jdbcType="INTEGER" />
    <result column="total_row" property="totalRow" jdbcType="INTEGER" />
    <result column="order_status" property="orderStatus" jdbcType="INTEGER"/>
  </resultMap>
  <sql id="ByMap_Where_Clause" ><dynamic prepend="where" >
      <isPropertyAvailable prepend="and" property="eqId" >
        id = #eqId:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="inIds" >
        id in ( $inIds$ )
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="inIdList" >
        id in <iterate prepend="" property="inIdList" open="(" close=")"
          conjunction=",">#inIdList[]:INTEGER#</iterate>
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="gtId" >
        id <![CDATA[ > ]]> #gtId:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="eqCurrDate" >
        curr_date <![CDATA[ = ]]> #eqCurrDate:DATE#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="eqLocalTotalAmount" >
        local_total_amount = #eqLocalTotalAmount:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="gtLocalTotalAmount" >
        local_total_amount <![CDATA[ > ]]> #gtLocalTotalAmount:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="eqLocalTotalRow" >
        local_total_row = #eqLocalTotalRow:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="gtLocalTotalRow" >
        local_total_row <![CDATA[ > ]]> #gtLocalTotalRow:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="eqTotalAmount" >
        total_amount = #eqTotalAmount:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="gtTotalAmount" >
        total_amount <![CDATA[ > ]]> #gtTotalAmount:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="eqTotalRow" >
        total_row = #eqTotalRow:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="and" property="gtTotalRow" >
        total_row <![CDATA[ > ]]> #gtTotalRow:INTEGER#
      </isPropertyAvailable>
    </dynamic>
  </sql>
  <sql id="ByMap_OrderBy_Clause" >
    <dynamic prepend="order by" >
      <isEqual property="sortName" compareValue="id" >
        id
      </isEqual>
      <isEqual property="sortName" compareValue="currDate" >
        curr_date
      </isEqual>
      <isEqual property="sortName" compareValue="localTotalAmount" >
        local_total_amount
      </isEqual>
      <isEqual property="sortName" compareValue="localTotalRow" >
        local_total_row
      </isEqual>
      <isEqual property="sortName" compareValue="totalAmount" >
        total_amount
      </isEqual>
      <isEqual property="sortName" compareValue="totalRow" >
        total_row
      </isEqual>
      <isEqual property="sortOrder" compareValue="desc" >
        DESC
      </isEqual>
      <isEqual property="sortOrder" compareValue="asc" >
        ASC
      </isEqual>
    </dynamic>
  </sql>
  <select id="selectById" resultMap="OrderCompareResult" parameterClass="java.lang.Integer" >
    select id, curr_date, local_total_amount, local_total_row, total_amount, total_row,order_status
    from t_uud_order_compare
    where id = #value#
  </select>
   <select id="getErrorRecords" resultMap="OrderCompareResult" parameterClass="java.util.Map" >
    select id, curr_date, local_total_amount, local_total_row, total_amount, total_row,order_status
    from t_uud_order_compare
    where order_status = #eqOrderStatus#
  </select>
  <select id="selectByMap" resultMap="OrderCompareResult" parameterClass="java.util.Map" >
    select id, curr_date, local_total_amount, local_total_row, total_amount, total_row,order_status
    from t_uud_order_compare
    <isParameterPresent >
      <include refid="t_uud_order_compare.ByMap_Where_Clause" />
      <isPropertyAvailable property="orderByClause" >
        order by $orderByClause$
      </isPropertyAvailable>
      <isPropertyAvailable property="sortName" >
        <include refid="t_uud_order_compare.ByMap_OrderBy_Clause" />
      </isPropertyAvailable>
      <include refid="BASE_DAO.pagination_End" />
    </isParameterPresent>
  </select>
  <select id="selectByMap_count" resultClass="int" parameterClass="java.util.Map" >
    select count(1) from t_uud_order_compare
    <isParameterPresent >
      <include refid="t_uud_order_compare.ByMap_Where_Clause" />
    </isParameterPresent>
  </select>
  <delete id="deleteById" parameterClass="java.lang.Integer" >
    delete from t_uud_order_compare
     where id = #value#
  </delete>
  <delete id="deleteByMap" parameterClass="java.util.Map" >
    delete from t_uud_order_compare
    <include refid="t_uud_order_compare.ByMap_Where_Clause" />
  </delete>
  <insert id="insert" parameterClass="cn._189.e.apipay.entity.OrderCompare" >
    insert into t_uud_order_compare (id, curr_date, local_total_amount, local_total_row,
      total_amount, total_row,order_status)
    values (#id:INTEGER#, #currDate:DATE#, #localTotalAmount:INTEGER#, #localTotalRow:INTEGER#,
      #totalAmount:INTEGER#, #totalRow:INTEGER#,#orderStatus:INTEGER#)
  </insert>
  <update id="updateById" parameterClass="cn._189.e.apipay.entity.OrderCompare" >
    update t_uud_order_compare
    set curr_date = #currDate:DATE#,
      local_total_amount = #localTotalAmount:INTEGER#,
      local_total_row = #localTotalRow:INTEGER#,
      total_amount = #totalAmount:INTEGER#,
      total_row = #totalRow:INTEGER#,
      order_status=#orderStatus:INTEGER#
    where id = #id:INTEGER#
  </update>
  <update id="updateByMap" parameterClass="java.util.Map" >
    update t_uud_order_compare
    <dynamic prepend="set" >
      <isPropertyAvailable prepend="," property="currDate" >
        curr_date = #currDate:DATE#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="," property="localTotalAmount" >
        local_total_amount = #localTotalAmount:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="," property="localTotalRow" >
        local_total_row = #localTotalRow:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="," property="totalAmount" >
        total_amount = #totalAmount:INTEGER#
      </isPropertyAvailable>
      <isPropertyAvailable prepend="," property="totalRow" >
        total_row = #totalRow:INTEGER#
      </isPropertyAvailable>
    </dynamic>
    <isParameterPresent >
      <include refid="t_uud_order_compare.ByMap_Where_Clause" />
    </isParameterPresent>
  </update>
</sqlMap>
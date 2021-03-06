package com.persistence.login.bean;

import java.util.ArrayList;
import java.util.List;

public class DataClearVersionEntityExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public DataClearVersionEntityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andVersionIsNull() {
            addCriterion("f_version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("f_version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("f_version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("f_version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("f_version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("f_version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("f_version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("f_version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("f_version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("f_version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("f_version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("f_version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("f_version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("f_version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("f_is_use is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("f_is_use is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(Integer value) {
            addCriterion("f_is_use =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(Integer value) {
            addCriterion("f_is_use <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(Integer value) {
            addCriterion("f_is_use >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_is_use >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(Integer value) {
            addCriterion("f_is_use <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(Integer value) {
            addCriterion("f_is_use <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<Integer> values) {
            addCriterion("f_is_use in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<Integer> values) {
            addCriterion("f_is_use not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(Integer value1, Integer value2) {
            addCriterion("f_is_use between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(Integer value1, Integer value2) {
            addCriterion("f_is_use not between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlIsNull() {
            addCriterion("f_update_url is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlIsNotNull() {
            addCriterion("f_update_url is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlEqualTo(String value) {
            addCriterion("f_update_url =", value, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlNotEqualTo(String value) {
            addCriterion("f_update_url <>", value, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlGreaterThan(String value) {
            addCriterion("f_update_url >", value, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlGreaterThanOrEqualTo(String value) {
            addCriterion("f_update_url >=", value, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlLessThan(String value) {
            addCriterion("f_update_url <", value, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlLessThanOrEqualTo(String value) {
            addCriterion("f_update_url <=", value, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlLike(String value) {
            addCriterion("f_update_url like", value, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlNotLike(String value) {
            addCriterion("f_update_url not like", value, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlIn(List<String> values) {
            addCriterion("f_update_url in", values, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlNotIn(List<String> values) {
            addCriterion("f_update_url not in", values, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlBetween(String value1, String value2) {
            addCriterion("f_update_url between", value1, value2, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateUrlNotBetween(String value1, String value2) {
            addCriterion("f_update_url not between", value1, value2, "updateUrl");
            return (Criteria) this;
        }

        public Criteria andUpdateContextIsNull() {
            addCriterion("f_update_context is null");
            return (Criteria) this;
        }

        public Criteria andUpdateContextIsNotNull() {
            addCriterion("f_update_context is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateContextEqualTo(String value) {
            addCriterion("f_update_context =", value, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextNotEqualTo(String value) {
            addCriterion("f_update_context <>", value, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextGreaterThan(String value) {
            addCriterion("f_update_context >", value, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextGreaterThanOrEqualTo(String value) {
            addCriterion("f_update_context >=", value, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextLessThan(String value) {
            addCriterion("f_update_context <", value, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextLessThanOrEqualTo(String value) {
            addCriterion("f_update_context <=", value, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextLike(String value) {
            addCriterion("f_update_context like", value, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextNotLike(String value) {
            addCriterion("f_update_context not like", value, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextIn(List<String> values) {
            addCriterion("f_update_context in", values, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextNotIn(List<String> values) {
            addCriterion("f_update_context not in", values, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextBetween(String value1, String value2) {
            addCriterion("f_update_context between", value1, value2, "updateContext");
            return (Criteria) this;
        }

        public Criteria andUpdateContextNotBetween(String value1, String value2) {
            addCriterion("f_update_context not between", value1, value2, "updateContext");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_data_clear_version
     *
     * @mbggenerated do_not_delete_during_merge Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_data_clear_version
     *
     * @mbggenerated Wed Aug 05 14:20:00 GMT+08:00 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
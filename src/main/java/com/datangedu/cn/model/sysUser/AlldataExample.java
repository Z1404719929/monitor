package com.datangedu.cn.model.sysUser;

import java.util.ArrayList;
import java.util.List;

public class AlldataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AlldataExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andAirportnameIsNull() {
            addCriterion("Airportname is null");
            return (Criteria) this;
        }

        public Criteria andAirportnameIsNotNull() {
            addCriterion("Airportname is not null");
            return (Criteria) this;
        }

        public Criteria andAirportnameEqualTo(String value) {
            addCriterion("Airportname =", value, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameNotEqualTo(String value) {
            addCriterion("Airportname <>", value, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameGreaterThan(String value) {
            addCriterion("Airportname >", value, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameGreaterThanOrEqualTo(String value) {
            addCriterion("Airportname >=", value, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameLessThan(String value) {
            addCriterion("Airportname <", value, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameLessThanOrEqualTo(String value) {
            addCriterion("Airportname <=", value, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameLike(String value) {
            addCriterion("Airportname like", value, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameNotLike(String value) {
            addCriterion("Airportname not like", value, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameIn(List<String> values) {
            addCriterion("Airportname in", values, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameNotIn(List<String> values) {
            addCriterion("Airportname not in", values, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameBetween(String value1, String value2) {
            addCriterion("Airportname between", value1, value2, "airportname");
            return (Criteria) this;
        }

        public Criteria andAirportnameNotBetween(String value1, String value2) {
            addCriterion("Airportname not between", value1, value2, "airportname");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("Year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("Year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("Year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("Year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("Year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("Year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("Year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("Year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("Year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("Year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("Year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("Year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(Integer value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(Integer value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(Integer value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(Integer value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(Integer value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<Integer> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<Integer> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(Integer value1, Integer value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("month not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andFreightvalueIsNull() {
            addCriterion("freightvalue is null");
            return (Criteria) this;
        }

        public Criteria andFreightvalueIsNotNull() {
            addCriterion("freightvalue is not null");
            return (Criteria) this;
        }

        public Criteria andFreightvalueEqualTo(Double value) {
            addCriterion("freightvalue =", value, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueNotEqualTo(Double value) {
            addCriterion("freightvalue <>", value, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueGreaterThan(Double value) {
            addCriterion("freightvalue >", value, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueGreaterThanOrEqualTo(Double value) {
            addCriterion("freightvalue >=", value, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueLessThan(Double value) {
            addCriterion("freightvalue <", value, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueLessThanOrEqualTo(Double value) {
            addCriterion("freightvalue <=", value, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueIn(List<Double> values) {
            addCriterion("freightvalue in", values, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueNotIn(List<Double> values) {
            addCriterion("freightvalue not in", values, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueBetween(Double value1, Double value2) {
            addCriterion("freightvalue between", value1, value2, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andFreightvalueNotBetween(Double value1, Double value2) {
            addCriterion("freightvalue not between", value1, value2, "freightvalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueIsNull() {
            addCriterion("passengervalue is null");
            return (Criteria) this;
        }

        public Criteria andPassengervalueIsNotNull() {
            addCriterion("passengervalue is not null");
            return (Criteria) this;
        }

        public Criteria andPassengervalueEqualTo(Double value) {
            addCriterion("passengervalue =", value, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueNotEqualTo(Double value) {
            addCriterion("passengervalue <>", value, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueGreaterThan(Double value) {
            addCriterion("passengervalue >", value, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueGreaterThanOrEqualTo(Double value) {
            addCriterion("passengervalue >=", value, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueLessThan(Double value) {
            addCriterion("passengervalue <", value, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueLessThanOrEqualTo(Double value) {
            addCriterion("passengervalue <=", value, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueIn(List<Double> values) {
            addCriterion("passengervalue in", values, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueNotIn(List<Double> values) {
            addCriterion("passengervalue not in", values, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueBetween(Double value1, Double value2) {
            addCriterion("passengervalue between", value1, value2, "passengervalue");
            return (Criteria) this;
        }

        public Criteria andPassengervalueNotBetween(Double value1, Double value2) {
            addCriterion("passengervalue not between", value1, value2, "passengervalue");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

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
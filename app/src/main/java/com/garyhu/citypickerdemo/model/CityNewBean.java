package com.garyhu.citypickerdemo.model;

import java.util.List;

/**
 * 作者： garyhu.
 * 时间： 2017/1/13.
 */

public class CityNewBean {

    private List<ProvinceBean> province;

    public List<ProvinceBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceBean> province) {
        this.province = province;
    }

    public static class ProvinceBean {
        /**
         * code : 710000
         * name : 台湾省
         * city : [{"code":"710100","name":"台北市","area":[{"code":"710101","name":"中正区"}]}]
         */

        private String code;
        private String name;
        private List<CityBean> city;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * code : 710100
             * name : 台北市
             * area : [{"code":"710101","name":"中正区"}]
             */

            private String code;
            private String name;
            private List<AreaBean> area;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<AreaBean> getArea() {
                return area;
            }

            public void setArea(List<AreaBean> area) {
                this.area = area;
            }

            public static class AreaBean {
                /**
                 * code : 710101
                 * name : 中正区
                 */

                private String code;
                private String name;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}

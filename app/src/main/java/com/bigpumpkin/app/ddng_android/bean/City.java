package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class City {


    private List<CountryBean> country;

    public List<CountryBean> getCountry() {
        return country;
    }

    public void setCountry(List<CountryBean> country) {
        this.country = country;
    }

    public static class CountryBean {
        /**
         * province : {"provinceCode":"110000","name":"北京","cities":[{"cityCode":"110100","name":"北京市","areas":[{"areaCode":"110101","name":"东城区"},{"areaCode":"110102","name":"西城区"},{"areaCode":"110105","name":"朝阳区"},{"areaCode":"110106","name":"丰台区"},{"areaCode":"110107","name":"石景山区"},{"areaCode":"110108","name":"海淀区"},{"areaCode":"110109","name":"门头沟区"},{"areaCode":"110111","name":"房山区"},{"areaCode":"110112","name":"通州区"},{"areaCode":"110113","name":"顺义区"},{"areaCode":"110114","name":"昌平区"},{"areaCode":"110115","name":"大兴区"},{"areaCode":"110116","name":"怀柔区"},{"areaCode":"110117","name":"平谷区"},{"areaCode":"110118","name":"密云区"},{"areaCode":"110119","name":"延庆区"}]}]}
         */

        private ProvinceBean province;

        public ProvinceBean getProvince() {
            return province;
        }

        public void setProvince(ProvinceBean province) {
            this.province = province;
        }

        public static class ProvinceBean {
            /**
             * provinceCode : 110000
             * name : 北京
             * cities : [{"cityCode":"110100","name":"北京市","areas":[{"areaCode":"110101","name":"东城区"},{"areaCode":"110102","name":"西城区"},{"areaCode":"110105","name":"朝阳区"},{"areaCode":"110106","name":"丰台区"},{"areaCode":"110107","name":"石景山区"},{"areaCode":"110108","name":"海淀区"},{"areaCode":"110109","name":"门头沟区"},{"areaCode":"110111","name":"房山区"},{"areaCode":"110112","name":"通州区"},{"areaCode":"110113","name":"顺义区"},{"areaCode":"110114","name":"昌平区"},{"areaCode":"110115","name":"大兴区"},{"areaCode":"110116","name":"怀柔区"},{"areaCode":"110117","name":"平谷区"},{"areaCode":"110118","name":"密云区"},{"areaCode":"110119","name":"延庆区"}]}]
             */

            private String provinceCode;
            private String name;
            private List<CitiesBean> cities;

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CitiesBean> getCities() {
                return cities;
            }

            public void setCities(List<CitiesBean> cities) {
                this.cities = cities;
            }

            public static class CitiesBean {
                /**
                 * cityCode : 110100
                 * name : 北京市
                 * areas : [{"areaCode":"110101","name":"东城区"},{"areaCode":"110102","name":"西城区"},{"areaCode":"110105","name":"朝阳区"},{"areaCode":"110106","name":"丰台区"},{"areaCode":"110107","name":"石景山区"},{"areaCode":"110108","name":"海淀区"},{"areaCode":"110109","name":"门头沟区"},{"areaCode":"110111","name":"房山区"},{"areaCode":"110112","name":"通州区"},{"areaCode":"110113","name":"顺义区"},{"areaCode":"110114","name":"昌平区"},{"areaCode":"110115","name":"大兴区"},{"areaCode":"110116","name":"怀柔区"},{"areaCode":"110117","name":"平谷区"},{"areaCode":"110118","name":"密云区"},{"areaCode":"110119","name":"延庆区"}]
                 */

                private String cityCode;
                private String name;
                private List<AreasBean> areas;

                public String getCityCode() {
                    return cityCode;
                }

                public void setCityCode(String cityCode) {
                    this.cityCode = cityCode;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<AreasBean> getAreas() {
                    return areas;
                }

                public void setAreas(List<AreasBean> areas) {
                    this.areas = areas;
                }

                public static class AreasBean {
                    /**
                     * areaCode : 110101
                     * name : 东城区
                     */

                    private String areaCode;
                    private String name;

                    public String getAreaCode() {
                        return areaCode;
                    }

                    public void setAreaCode(String areaCode) {
                        this.areaCode = areaCode;
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
}

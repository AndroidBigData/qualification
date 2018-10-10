package com.zjwam.qualification.bean;

import java.io.Serializable;
import java.util.List;

public class ClassSearchBean {
    /**
     * list : [{"name":"小学","id":12,"cont":[{"id":27,"name":"一年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"}]},{"id":28,"name":"二年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"}]},{"id":29,"name":"三年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":30,"name":"四年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":31,"name":"五年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":32,"name":"六年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":33,"name":"小升初","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":34,"name":"专题冲刺","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]}]},{"name":"初中","id":13,"cont":[{"id":35,"name":"七年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":36,"name":"八年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"},{"id":61,"name":"物理"}]},{"id":37,"name":"九年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"},{"id":61,"name":"物理"},{"id":62,"name":"化学"}]}]},{"name":"高中","id":14,"cont":[{"id":40,"name":"高一","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"},{"id":61,"name":"物理"},{"id":62,"name":"化学"},{"id":63,"name":"政治"},{"id":64,"name":"历史"},{"id":65,"name":"地理"},{"id":66,"name":"生物"}]},{"id":41,"name":"高二","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"},{"id":61,"name":"物理"},{"id":62,"name":"化学"},{"id":63,"name":"政治"},{"id":64,"name":"历史"},{"id":65,"name":"地理"},{"id":66,"name":"生物"}]},{"id":42,"name":"高三","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"},{"id":61,"name":"物理"},{"id":62,"name":"化学"},{"id":64,"name":"历史"},{"id":65,"name":"地理"},{"id":66,"name":"生物"}]}]},{"name":"国学","id":15,"cont":[]},{"name":"夏/冬令营","id":16,"cont":[]},{"name":"职业规划","id":17,"cont":[]}]
     */

    private String name;
    private int id;
    private List<ListBean> cont;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<ListBean> getCont() {
        return cont;
    }

    public static class ListBean {
        /**
         * name : 小学
         * id : 12
         * cont : [{"id":27,"name":"一年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"}]},{"id":28,"name":"二年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"}]},{"id":29,"name":"三年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":30,"name":"四年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":31,"name":"五年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":32,"name":"六年级","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":33,"name":"小升初","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]},{"id":34,"name":"专题冲刺","cont":[{"id":58,"name":"语文"},{"id":59,"name":"数学"},{"id":60,"name":"英语"}]}]
         */

        private String name;
        private int id;
        private List<ContBeanX> cont;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ContBeanX> getCont() {
            return cont;
        }

        public void setCont(List<ContBeanX> cont) {
            this.cont = cont;
        }

        public static class ContBeanX {
            /**
             * id : 27
             * name : 一年级
             * cont : [{"id":58,"name":"语文"},{"id":59,"name":"数学"}]
             */

            private int id;
            private String name;
            private List<ContBean> cont;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ContBean> getCont() {
                return cont;
            }

            public void setCont(List<ContBean> cont) {
                this.cont = cont;
            }

            public static class ContBean {
                /**
                 * id : 58
                 * name : 语文
                 */

                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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

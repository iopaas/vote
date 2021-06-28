<template>
    <transition :name="transitionName">
        <div v-show="visible" :style="customStyle" class="back-to-ceiling" @click="backToTop">
            <svg
                t="1592800077020"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="1136"
                width="16"
                height="16"
            >
                <path d="M30.332842 0.346901l963.333292 0 0 74.102718-963.333292 0 0-74.102718Z" p-id="1137" fill="#4F82FA"></path>
                <path
                    d="M564.392254 162.53684 512 110.143563 459.606723 162.53684 459.606723 162.53684 119.017674 503.125889 171.409928 555.519166 474.948129 251.980965 474.948129 1023.653099 549.050848 1023.653099 549.050848 251.980965 852.589049 555.519166 904.982326 503.125889 564.392254 162.53684Z"
                    p-id="1138"
                    fill="#4F82FA"
                ></path>
            </svg>
            <div>顶部</div>
            <!-- <svg width="16" height="16" viewBox="0 0 17 17" xmlns="http://www.w3.org/2000/svg" class="Icon Icon--backToTopArrow" aria-hidden="true" style="height:16px;width:16px"><path d="M12.036 15.59a1 1 0 0 1-.997.995H5.032a.996.996 0 0 1-.997-.996V8.584H1.03c-1.1 0-1.36-.633-.578-1.416L7.33.29a1.003 1.003 0 0 1 1.412 0l6.878 6.88c.782.78.523 1.415-.58 1.415h-3.004v7.004z" /></svg> -->
        </div>
    </transition>
</template>

<script>
export default {
    name: 'BackToTop',
    props: {
        visibilityHeight: {
            type: Number,
            default: 400,
        },
        backPosition: {
            type: Number,
            default: 0,
        },
        customStyle: {
            type: Object,
            default: function() {
                return {
                    right: '50px',
                    bottom: '50px',
                    width: '45px',
                    height: '45px',
                    'border-radius': '4px',
                    // 'line-height': '45px',
                    background: '#e7eaf1',
                };
            },
        },
        transitionName: {
            type: String,
            default: 'fade',
        },
    },
    data() {
        return {
            visible: false,
            interval: null,
            isMoving: false,
        };
    },
    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },
    beforeDestroy() {
        window.removeEventListener('scroll', this.handleScroll);
        if (this.interval) {
            clearInterval(this.interval);
        }
    },
    methods: {
        handleScroll() {
            this.visible = window.pageYOffset > this.visibilityHeight;
        },
        backToTop() {
            if (this.isMoving) return;
            const start = window.pageYOffset;
            let i = 0;
            this.isMoving = true;
            this.interval = setInterval(() => {
                const next = Math.floor(this.easeInOutQuad(10 * i, start, -start, 500));
                if (next <= this.backPosition) {
                    window.scrollTo(0, this.backPosition);
                    clearInterval(this.interval);
                    this.isMoving = false;
                } else {
                    window.scrollTo(0, next);
                }
                i++;
            }, 16.7);
        },
        easeInOutQuad(t, b, c, d) {
            if ((t /= d / 2) < 1) return (c / 2) * t * t + b;
            return (-c / 2) * (--t * (t - 2) - 1) + b;
        },
    },
};
</script>

<style scoped>
.back-to-ceiling {
    position: fixed;
    /* display: inline-block; */
    text-align: center;
    cursor: pointer;
    box-shadow: 0px 6px 21px 8px rgba(0, 0, 0, 0.03);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.back-to-ceiling > div {
    margin-top: 5px;
    font-size: 12px;
    line-height: 12px;
    color: #4f82fa;
}

.back-to-ceiling:hover {
    background: #d5dbe7;
}

.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
    opacity: 0;
}

.back-to-ceiling .Icon {
    fill: #9aaabf;
    background: none;
}
</style>

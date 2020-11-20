<template>
    <article>
        <a href="#page=Post" @click="onViewPost(post)" class="title">{{post.title}}</a>
        <div class="information">By {{post.user.name}}</div>
        <div class="body">
            {{post.text}}
        </div>
        <div class="footer">
            <div class="left">
                <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+over9000</span>
                <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                {{post.creationTime}}
                <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#page=Post" @click="onViewPost(post)">{{post.comments.length}}</a>
            </div>
        </div>
    </article>
</template>

<script>
    export default {
        name: "IndexPost",
        props: ["post"],
        methods: {
            onViewPost: function (post) {
                this.$root.$emit("onChangePage", "Post", post);
            }
        }
    }
</script>

<style scoped>

</style>
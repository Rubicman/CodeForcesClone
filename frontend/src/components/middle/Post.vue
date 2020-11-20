<template>
    <article>
        <IndexPost :post="post"/>
        <div class="comments">
            <div class="commentsTitle">
                <img src="../../assets/img/comments-48x48.png" title="Comments" alt="Comments"/>
                <p>Comments({{post.comments.length}})</p>
            </div>
            <div class="comment" v-for="comment in post.comments" :key="comment.id">
                <div class="information">By {{comment.user.name}}, {{comment.creationTime}}</div>
                <div class="body">
                    {{comment.text}}
                </div>
            </div>
        </div>
    </article>
</template>

<script>
    import IndexPost from "./IndexPost";

    export default {
        name: "Post",
        props: ["post"],
        components: {
            IndexPost
        }
    }
</script>

<style scoped>

</style>
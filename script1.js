    const toggleSidebar=()=>{
    if($(".sidebar").is(":visible"))
    {
        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%")
    }
    else{
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%")
    }

};

const search=()=>{
    let query=$("#search-input").val();
    

    if(query=='')
    {
        $(".search-result").hide();

    }
    else{
        //search
        console.log(query);

        let url=`http://localhost:8080/search/${query}`;

        fetch(url).then((response)=>{
            return response.json();

        }).then((data)=>{
            //data
            console.log(data);
            let text=`<div class='list-group'>`
            
            data.forEach(product=>{
				text+=`<a href='/user/${product.pid}/product' class='list-group-item list-group-item-action'>${product.productName} </a>`
			})
            text+=`</div>`
            
            $(".search-result").html(text);
            $(".search-result").show();
        });

        $(".search-result").show();

    }
}
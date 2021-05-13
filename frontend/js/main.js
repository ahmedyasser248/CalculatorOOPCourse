const app = Vue.createApp({
    data (){
        return{
            text:'',
            Display:'',
            Result:'',
            operand1 : '',
            operand2 : '',
            operator:'',
            isNegative:'',
            isOperator:false,
            Equal:false,
            count:0
        }
    },
    methods: {
         async getData(){
             let response =await fetch("http://localhost:8080/get")
             console.log(response.status); // 200
             console.log(response.statusText); // OK
             

             if (response.status === 200) {
                let data = await response.text();
                    this.Display=data
                    this.operand1=data
                    console.log( "from get data operand is : "+this.operand1)   
            }
        },
        async postData1(equation){
            console.log(equation)
            let response =await fetch("http://localhost:8080/"+this.text,{
           method : 'POST',
           body: equation
        }).catch(e=>console.log(e))
        console.log(response.status); // 200
        console.log(response.statusText); // OK
        },
        addToDisplay(letter ){
            this.checkDisplay()
            if(this.count==0){
                if(letter=="."){
                    if(this.operand1.includes(".")){
                        return;
                    }
                }
                this.operand1=this.operand1+letter
                this.Display=this.operand1
                console.log("operand 1 :"+this.operand1)
                

            }else{
                if(letter=="."){
                    if(this.operand2.includes(".")){
                        return
                    }
                }
                this.operand2=this.operand2+letter
                this.Display=this.operand2
                console.log("operand 2 : "+this.operand2)
            }
           
        },
         async addOperator(operator){
      
             if(this.operand1.length==0&&operator=='-'){
                this.operand1=this.operand1+operator
                this.Display=this.operand1
            }

            else if(this.operand2.length==0&&this.operand1!='-'&&this.operand1.length!=0){
               this.operator=operator
               this.count=1
            }else if(this.operand1.length!=0&&this.operand2.length!=0&&this.operator.length!=0){
                this.text="evaluate"
               await this.postData1(this.operand1+" "+this.operator+" "+this.operand2)
               await this.getData() 
               this.setAfterCalculations(operator)
            }

        },
        clearAll(){
            this.Result=''
            this.Display=''
            this.isOperator=false
            this.count=0
            this.operand1=''
            this.operand2=''
            this.operator=''
        },
        setAfterCalculations(operator){
            console.log("are you called?")
            this.operand2=''
            this.operator=operator
        },
        async evaluatePower(){
            if(this.operand1.length != 0&&this.operand1!="E"&&this.operand1!="-"){
               this.text="power"
               await this.postData1(this.operand1+" "+this.operator+" "+this.operand2)
               await this.getData()
               this.operator=""
               this.operand2=""
            }
        },
        async evaluateRoot(){
            if(this.operand1.length != 0&&this.operand1!="E"&&this.operand1!="-"){
                this.text="root"
                await this.postData1(this.operand1+" "+this.operator+" "+this.operand2)
                await this.getData()
                this.operator=""
                this.operand2=""
             }
            

        },
       async pressEqual(){
            if(this.operand1.length!=0&&this.operand2.length!=0&&this.operator.length!=0){
                this.text="evaluate"
               await this.postData1(this.operand1+" "+this.operator+" "+this.operand2)
                await this.getData()
                this.setAfterEqual()
            }
        },
        async evaluateInverse(){
            if(this.operand1.length!=0&&this.operand1!="E"&&this.operand1!="-"){
                this.text="inverse"
                await this.postData1(this.operand1+" "+this.operator+" "+this.operand2)
                await this.getData()
                this.operator=""
            this.operand2=""
         
            }

        },
        setAfterEqual(){
            
            this.operator=""
            this.operand2=""
            this.count=0
            console.log(this.Equal)
        },
        checkDisplay(){
           if( this.Display=="E"){
               this.count=0
               this.operand1=""
           }
        },
        clearOne(){
            if(this.Display.length!=0){
                if(this.operand2.length!=0){
                    this.operand2=this.operand2.slice(0,-1)
                    this.Display=this.operand2

                }else if(this.operand1.length!=0&&this.operator.length==0){
                    this.operand1=this.operand1.slice(0,-1)
                    this.Display=this.operand1
                }
                

            }
        },
        switchSign(){
            if(this.operand2.length!=0){
                if(this.operand2.includes("-")){
                    this.operand2=this.operand2.substring(1)
                    this.Display=this.operand2
                }else{
                    this.operand2="-"+this.operand2
                    this.Display=this.operand2
                }
            }
            else if(this.operand1.length!=0){
                if(this.operand1.includes("-")){
                    this.operand1=this.operand1.substring(1)
                    this.Display=this.operand1
                }else{
                    this.operand1="-"+this.operand1
                    this.Display=this.operand1
                }  
            }
        },
        async calcPercentage(){
            if(this.operand1.length!=0){
                this.text="percentage"
               await this.postData1(this.operand1+" "+this.operator+" "+this.operand2)
               await this.getData()
               this.operator=""
               this.operand2=""
               this.count=0;

            }
        }
    },

})